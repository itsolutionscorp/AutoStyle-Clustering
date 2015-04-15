class Robot
  def self.sampler(arr)
    Enumerator.new { |y| loop { y << arr.sample } }
  end
  private_class_method :sampler

  LETTERS = sampler("ABCDEFGHIJKLMNOPQRSTUVWXYZ".chars.to_a)
  NUMBERS = sampler((0..9).to_a)

  def name
    @name ||= LETTERS.take(2).concat(NUMBERS.take(3)).join('')
  end
  
  def reset
    @name = nil
  end
end
