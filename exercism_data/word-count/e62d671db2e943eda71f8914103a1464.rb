class Phrase < Struct.new(:phrase)

  def word_count
    words.count
  end

  private
  def words
    Words.from_string(phrase).clean!
  end
  
  class Words < Struct.new(:words)
    include Enumerable

    def self.from_string(string)
      new(split_on_spaces_and_commas(string))
    end

    def each(&block)
      words.each(&block)
    end

    def count
      each_with_object(Hash.new(0)) do |word, result|
        result[word] += 1
      end
    end
      
    def clean!
      normalize!
      .without_punctuation!
      .without_blank_words!
    end

    protected
    def normalize!
      self.class.new(map(&:downcase))
    end

    def without_punctuation!
      self.class.new(map{|word| word.gsub(/[^a-z0-9]/i, '') })
    end

    def without_blank_words!
      self.class.new(reject{|word| word.empty? })
    end

    private
    def self.split_on_spaces_and_commas(string)
      string.split(/\s|\,/)
    end
  end
end
