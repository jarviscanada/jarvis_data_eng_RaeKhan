package ca.jrvs.apps.practice;

interface RegexExc2 {
    /**
     * return true if filename extension is jpg or jpeg, case sensitive
     * @param filename
     * @return
     */
    public boolean matchJpeg(String filename);
    /**
     * return true if ip is valid
     * to simplify the problem, IP address range is from 0.0.0.0 to 999.999.999.999
     * @param ip
     * @return
     */
    public boolean matchIp(String ip);
    /**
     * return true if line is empty (eg. empty, white space, tabs, etc...)
     * @param line
     * return
     */
    public boolean isEmptyLine(String line);
}

class RegExc implements RegexExc2 {

    public boolean matchJpeg(String filename){
        if (filename.matches("(.*)jpg(.*)|(.*)jpeg(.*)")) {
            return true;
        }
        return false;
    }

    public boolean matchIp(String ip){
        if ((ip.matches("([0-9]|[0-9][0-9]|[0-9][0-9][0-9]).([0-9]|[0-9][0-9]|[0-9][0-9][0-9]).([0-9]|[0-9][0-9]|[0-9][0-9][0-9])"))) {
            return true;
        }
        return false;
    }

    public boolean isEmptyLine(String line){
//        if (line.matches("^\s*$")) {
//            return true;
//        }
        return false;
    }

    public static void main(String[] args) {
        RegexExc2 jpeg = new RegExc();
        System.out.println(jpeg.matchJpeg("filename.jpeg"));
        RegexExc2 ip = new RegExc();
        System.out.println(jpeg.matchIp("12.34.56"));
        RegexExc2 line = new RegExc();
        System.out.println(line.isEmptyLine("       "));
    }
}
