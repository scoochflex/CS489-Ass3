import java.util.List;

public interface FileShareService {
	
	public void registerFile(String filename, String path, String clientAddress, long size);

	public boolean unRegisterFile(int fid);

	public List<String[]>  getAllSharedFiles();

	public String[][] searchFilesByName(String filename);
	
	public String[][] searchFilesByAddress(String address);
}