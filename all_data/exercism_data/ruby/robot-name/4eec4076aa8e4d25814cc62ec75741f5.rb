module RandomName
  def self.next
    chars.join
  end

  private

  def self.chars
    of(['A','Z']).take(2) + of(0..9).take(3)
  end

  def self.of(options)
    return to_enum(:of, options) unless block_given?
    loop { yield options.to_a.sample }
  end
end

class Robot
  def initialize
    reset
  end

  def reset
    @name = RandomName.next
  end

  attr_reader :name
end
