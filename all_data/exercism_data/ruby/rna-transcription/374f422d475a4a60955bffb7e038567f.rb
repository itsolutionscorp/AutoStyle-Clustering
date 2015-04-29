class Complement
  class << self
    
    def of_dna(strand)
      split_yield_join(strand) do |ntide, array|
        array << { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }[ntide]      
      end
    end

    def of_rna(strand)
      split_yield_join(strand) do |ntide, array|
        array << { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }[ntide]      
      end
    end

    private

    def split_yield_join(string)
      string.split(//).each_with_object([]){ |char, array| yield char, array }.join
    end
    
  end
end
