package blockchain;

import com.google.gson.GsonBuilder;
import java.util.ArrayList;

/**
 *
 * @author BUS118
 */
public class Blockchain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;

	public static void main(String[] args) {
            /*
            Add blocks to the blockchain
            */
            blockchain.add(new Block("Hi im the first block", "0"));
            System.out.println("Trying to Mine block 1... ");
            blockchain.get(0).mineBlock(difficulty);
            blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
            System.out.println("Trying to Mine block 2... ");
            blockchain.get(1).mineBlock(difficulty);
            blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));
            System.out.println("Trying to Mine block 3... ");
            blockchain.get(2).mineBlock(difficulty);	
                
            /*
            Checks to verify that blockchain is valid
            */
            System.out.println("\nBlockchain is Valid: " + isChainValid());
            
            /*
            Display blockchain with information
            */
            String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
            System.out.println("\nThe block chain: ");
            System.out.println(blockchainJson);
	}
	
        /*
        Checks Blockchain validity 
        */
	public static Boolean isChainValid() {
            
            Block currentBlock;
            Block previousBlock;
            String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
            /*
            Loop through blockchain and check Hashes
            */
            for(int i=1; i < blockchain.size(); i++) {
            	currentBlock = blockchain.get(i);
            	previousBlock = blockchain.get(i-1);
                
            	/*
                Compares registred hash and computed hash
                */
            	if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
            		System.out.println("Current Hashes not equal");			
            		return false;
            	}
                
                /*
                Compares previous hash and registed hash
                */
            	if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
            		System.out.println("Previous Hashes not equal");
            		return false;
            	}
                
            	/*
                Check if hash is solved
                */
            	if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
            		System.out.println("This block hasn't been mined");
            		return false;
            	}
            }
            return true;
    }
}