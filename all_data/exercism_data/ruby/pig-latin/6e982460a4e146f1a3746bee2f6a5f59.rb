class PigLatin
  def self.translate(phrase)
    words = phrase.split(' ')

    words.collect do |word|
      Word.new(word).convert
    end.join(' ')
  end

  class Word
    def initialize(string)
      @word = string
    end

    def convert
      if start_with_vowel_sound?
        number_of_times_to_rotate = 0
      elsif start_with_trigraph?
        number_of_times_to_rotate = 3
      elsif start_with_digraph?
        number_of_times_to_rotate = 2
      else
        number_of_times_to_rotate = 1
      end

      @word.chars.rotate(number_of_times_to_rotate).join + "ay"
    end

    def start_with_vowel_sound?
      @word.start_with?('a', 'e', 'i', 'o', 'u', 'yt', 'xr')
    end

    def start_with_digraph?
      @word.start_with?('ch', 'qu', 'th')
    end

    def start_with_trigraph?
      @word.start_with?('squ', 'thr', 'sch')
    end
  end
end
