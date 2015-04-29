class PigLatin
  VOWELS = ['a', 'e', 'i', 'o', 'u']

  def self.translate message
    message.split(' ').map { |word| self.translate_word word }.join ' '
  end

  def self.translate_word word
    leading_cluster = word.chars.each_with_index.take_while do |char, index|
      self.leading_consonant? char, index, word
    end.map(&:first).join
    word[leading_cluster.length..-1] + leading_cluster + 'ay'
  end

  def self.leading_consonant? char, index, word
    vowel_xy = (char == 'x' || char == 'y') && !VOWELS.include?(word[index+1])
    is_qu = index > 0 && word[index - 1] == 'q' && char == 'u'
    return (!VOWELS.include?(char) || is_qu) && !vowel_xy 
  end
end
