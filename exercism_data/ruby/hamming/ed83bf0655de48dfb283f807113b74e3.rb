class Hamming
  
  class << self
    
    def compute(str1, str2)
      prepare_globals(str1, str2)
      compare_dna
      calculate_dna_differences
    end

    private

    def prepare_globals(str1, str2)
      @result = []
      @sum = 0
      @dna1 = arrify_string(str1)
      @dna2 = second_string_sized_to_match(@dna1, arrify_string(str2)) 
    end

    def compare_dna
       @dna1.count.times do |sequence|
        @result[sequence] = @dna1[sequence] != @dna2[sequence] unless @dna2[sequence] == nil
      end
    end

    def calculate_dna_differences
      @result.map { |e| @sum += 1 if e }
      @sum
    end

    def second_string_sized_to_match(str1, str2)
      str2.take(str1.length)
    end

    def arrify_string(str)
      str.split(//)
    end

  end

end
