class Hamming
  class Comparator
    attr_reader :combined_strands
    def initialize stranda, strandb
      @combined_strands = stranda.chars.zip(strandb.chars)
    end
    
    def mutations
      combined_strands.count{|acid_pair| is_mutated *acid_pair }
    end
    
    private
    def is_mutated acida, acidb
      !(acida == acidb || acida.nil? || acidb.nil?)
    end
  end
  
  def self.compute stranda, strandb
    Comparator.new(stranda,strandb).mutations
  end
end
