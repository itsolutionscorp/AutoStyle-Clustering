class DNA

  def initialize(text)
    @text = text.chars
    nucleotide_counts
  end

  def nucleotide_counts
    @expected = { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }
    oops = 'yup'
    @text.each do |x|
      if is_nucleotide?(x)
        @expected[x] += 1
      else
        oops = nil
      end
    end
    throw ArgumentError unless oops
    @expected
  end
 
  def count(l)
  	throw ArgumentError unless is_nucleotide?(l) || is_uracil?(l)
  	nucleotide_counts
    unless is_uracil?(l)
      @expected[l]
    else
      0
    end
  end


  private

  def is_a?(letter)
    letter == 'A'
  end

  def is_t?(letter)
    letter == 'T'
  end

  def is_c?(letter)
    letter == 'C'
  end

  def is_g?(letter)
    letter == 'G'
  end

  def is_nucleotide?(letter)
    is_a?(letter) || is_t?(letter) || is_c?(letter) || is_g?(letter)
  end

  def is_uracil?(letter)
    letter == 'U'
  end

end
