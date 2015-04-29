class Hamming

  def initialize(first_strand, second_strand)
    @first_strand = first_strand
    @second_strand = second_strand
    @distance = 0
  end

  def self.compute(first_strand, second_strand)
    self.new(first_strand, second_strand).compute
  end

  def compute
    @first_strand.chars.each_with_index do |char, index|
      compare_chars(index, char)
    end
    @distance
  end

  private

  def compare_chars(index, char)
    if has_char?(index) && chars_different?(char, @second_strand[index])
      @distance +=1 
    end
  end

  def has_char?(index)
    @second_strand[index]
  end

  def chars_different?(char, oth_char)
    char != oth_char
  end
end
