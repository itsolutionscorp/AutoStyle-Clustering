class Complement
  @dr_array = [['G', 'C'], ['C', 'G'], ['T', 'A'], ['A', 'U']]

  def self.of_rna(strand)
     replace_values(strand,false)
  end

  def self.of_dna(strand)
    replace_values(strand,true)
  end

  def self.replace_values(strand, val) 
    arr_of_strand = strand.chars
    @test = (val == true) ? @dr_array : @dr_array.map{|z|z.reverse}
    arr_of_strand.each do |char|
      case char
      when @test[0][0]
        char.replace @test[0][1]
      when @test[1][0]
        char.replace @test[1][1]
      when @test[2][0]
        char.replace @test[2][1]
      when @test[3][0]
        char.replace @test[3][1]
      else
        raise ArgumentError
      end
    end
     arr_of_strand.join('')
  end
end
