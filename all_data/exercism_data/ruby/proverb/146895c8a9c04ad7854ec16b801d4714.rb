# class Proverb
class Proverb
  def initialize(*words)
    @qual = words.pop[:qualifier] if words.last.is_a? Hash
    @words = words
  end

  def to_s
    @sentence = ''
    @words.each_cons(2) do | nail, shoe |
      @sentence += "For want of a #{nail} the #{shoe} was lost.\n"
    end
    @sentence += "And all for the want of a #{last_word}."
    @sentence
  end

  def last_word
    @qual ? 'horseshoe nail' : @words.first
  end
end
