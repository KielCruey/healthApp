package assignment2.healthapp;

public class CTTest {
    public CTTest() {
        this.patientID = -1;
        this.LMScore = -1;
        this.LADScore = -1;
        this.LCXScore = -1;
        this.RCAScore = -1;
        this.PDAScore = -1;
        this.totalCACScore = -1;
    }

    public CTTest(int patientID, int LMScore, int LADScore, int LCXScore, int RCAScore, int PDAScore, int totalCACScore) {
        this.patientID = patientID;
        this.LMScore = LMScore;
        this.LADScore = LADScore;
        this.LCXScore = LCXScore;
        this.RCAScore = RCAScore;
        this.PDAScore = PDAScore;
        this.totalCACScore = totalCACScore;
    }

    private int patientID;
    private int LMScore;
    private int LADScore;
    private int LCXScore;
    private int RCAScore;
    private int PDAScore;
    private int totalCACScore;

    // getters/setters
    public int getPatientID() { return this.patientID; };
    public int getLMScore() { return this.LMScore; };
    public int getLADScore() { return this.LADScore; };
    public int getLCXScore() { return this.LCXScore; };
    public int getRCAScore() { return this.RCAScore; };
    public int getPDAScore() { return this.PDAScore; };
    public int getTotalCACScore() { return this.totalCACScore; };

    public void setPatientID(int patientID) { this.patientID = patientID; };
    public void setLMScore(int LMScore) { this.LMScore = LMScore; };
    public void setLADScore(int LADScore) { this.LADScore = LADScore; };
    public void setLCXScore(int LCXScore) { this.LCXScore = LCXScore; };
    public void setRCAScore(int RCAScore) { this.RCAScore = RCAScore; };
    public void setPDAScore(int PDAScore) { this.PDAScore = PDAScore; };
    public void setTotalCACScore(int totalCACScore) { this.totalCACScore = totalCACScore; };

    private void storeCTScanData() {

    }

    private void getCACScores() {

    }
}