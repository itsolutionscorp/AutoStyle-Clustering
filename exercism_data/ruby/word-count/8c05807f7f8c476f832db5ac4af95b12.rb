class Phrase
  attr_accessor :source
  attr_reader   :hash
  
  def initialize source
    @source = PhraseCleaner.clean source
    @hash   = Hash.new 0
  end

  def word_count
    unless @hash.empty?
      @hash
    else
      arr = split
      arr.each do |word|
        @hash[word] += 1
      end
      return @hash
    end
  end

  private

  def split
    @source.split
  end
end

class PhraseCleaner
  class << self
    attr_reader :source

    def clean source
      @source = source
      substitute_commas
      delete_punctuation
      downcase
      @source
    end

    private

    def substitute_commas
      @source.gsub! ',', ' '
      @source.squeeze
    end
  
    def downcase
      @source = @source.downcase
    end
  
    def delete_punctuation
      @source.gsub! /[^\w\d,'\s]/, '' 
    end
  end
end
