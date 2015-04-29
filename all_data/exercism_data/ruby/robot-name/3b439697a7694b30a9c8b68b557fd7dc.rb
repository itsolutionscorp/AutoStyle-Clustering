require 'radix'
require 'set'

class Robot
  @alphabet = ('A'..'Z').to_a
  @store = Set.new

  def name
    @name ||= Robot.next
  end

  def reset
    @name = nil
  end

  private

  def self.next
    begin
      name = generate
    end while @store.add? name
    name
  end

  def self.generate
    rand(@alphabet.size..@alphabet.size ** 2 - 1).b(@alphabet.size).to_s(@alphabet) + rand(100..999).to_s
  end
end
