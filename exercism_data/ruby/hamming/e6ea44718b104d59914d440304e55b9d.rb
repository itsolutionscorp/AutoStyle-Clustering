class Hamming
  def self.compute stranda, strandb
    combinedStrands(stranda, strandb).count{|pair| isMutated *pair }
  end
  
  private
  def self.isMutated acida, acidb
    !(acida == acidb || acida.nil? || acidb.nil?)
  end
  
  def self.combinedStrands stranda, strandb
    stranda.chars.zip(strandb.chars)
  end
end
