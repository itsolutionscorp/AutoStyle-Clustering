class PigLatin
  def self.translate(word)
    @word = word
    if @word.start_with?('a', 'e', 'i', 'o', 'u')
      @word + "ay"
    elsif @word.start_with?('ch')
      @word.chars.rotate(2).join + 'ay'
    else
      @word.chars.rotate(1).join + 'ay'
    end
  end
end
