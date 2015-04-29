class Phrase
  def initialize(string)
    @word_count = {}
    filtered_string = remove_punctuation(string)
    create_word_count(filtered_string)
  end

  def word_count
    @word_count
  end

  def create_word_count(string)
    string.split(" ").each do | word |
      if word.include?(",")
        add_to_word_count(word.split(","))
      else
        add_to_word_count([word])
      end
    end
  end

  def remove_punctuation(string)
    punctuation = ["!",":","&","@","$","%","^","."]
    punctuation.each do | punc |
      string.gsub!(punc,"")
    end
    string
  end

  def add_to_word_count(word_array)
    word_array.each do | word |
      if @word_count.has_key? word.downcase
        @word_count[word.downcase] += 1
      else
        @word_count[word.downcase] = 1
      end
    end
  end

end
