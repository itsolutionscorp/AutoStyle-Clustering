class Hamming
  class Comparator
    attr_reader :combined_strands
    def initialize stranda, strandb
      @stranda = stranda
      @strandb = strandb
    end
    
    def mutations
      combined_strands.count{|acid_pair| is_mutated *acid_pair }
    end
    
    private
    
    def combined_strands
      @stranda.chars.zip(@strandb.chars).take(min_length)
    end
    
    def is_mutated acida, acidb
      acida != acidb
    end
    
    def min_length
      if @stranda.length < @strandb.length
        @stranda.length
      else
        @strandb.length
      end
    end
  end
  
  def self.compute stranda, strandb
    Comparator.new(stranda,strandb).mutations
  end
end
