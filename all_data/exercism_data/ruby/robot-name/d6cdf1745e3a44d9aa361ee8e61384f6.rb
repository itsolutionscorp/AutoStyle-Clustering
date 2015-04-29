require 'radix'

class Robot
  @alphabet = ('A'..'Z').to_a
  @store = {}

  def name
    @name ||= Robot.next
  end

  def reset
    @name = nil
  end

  private

  def self.next
    while @store[name = generate] ; end
    @store[name] = 1
    name
  end

  def self.generate
    rand(@alphabet.size..@alphabet.size ** 2 - 1).b(@alphabet.size).to_s(@alphabet) + rand(100..999).to_s
  end
end
