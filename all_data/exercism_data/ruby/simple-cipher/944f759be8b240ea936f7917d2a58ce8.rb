class Cipher
  attr_reader :key
  def initialize(string)
    @key = string || generate_random_key
  end

private

  def generate_random_key
    letters = ('a'..'z').to_a
    100.times.map{ letters[rand(26)] }.join
  end
end
