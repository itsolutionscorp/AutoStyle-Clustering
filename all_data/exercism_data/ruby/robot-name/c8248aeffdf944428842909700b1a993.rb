class Robot
  def name
    @name ||= "#{model}#{unit}"
  end
  
  def reset
    @name = nil
  end
  
  private
  
  MODEL_LENGTH = 2
  UNIT_LENGTH = 3
  A = 65
  ALPHABET_LETTERS = 26
  MAX_UNIT_NUMBER = 999
  
  def model
    (1..MODEL_LENGTH).collect { random_letter }.join
  end
  
  def unit
    "%0#{UNIT_LENGTH}d" % random_number
  end
  
  def random_letter
    (A + rand(ALPHABET_LETTERS)).chr
  end
  
  def random_number
    rand(MAX_UNIT_NUMBER)
  end
end
