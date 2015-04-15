class Robot

  require 'pry'
  def initialize
    @random_letters = [('a'..'z'), ('A'..'Z')].map { |i| i.to_a  }.flatten
    reset
  end

  def name
    @name
  end

  def reset
    string = (0..1).map { @random_letters[rand(@random_letters.length)] }.join
    @name = string + (100..999).to_a.sample.to_s
  end

end
