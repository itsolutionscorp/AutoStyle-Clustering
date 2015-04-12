class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    parse(@phrase)
  end

  private

  def parse(string)
    @word_list = {}
    clean_array = clean_up_string(string) 
    clean_array.each do |word| 
      add_to_existing_word(word) if @word_list.include?(word.downcase) 
      add_word_to_list(word) if !@word_list.include?(word.downcase) 
    end
    @word_list
  end

  def add_to_existing_word(word)
    @word_list[word.downcase] += 1
  end

  def add_word_to_list(word)
    @word_list[word.downcase] = 1
  end

  def clean_up_string(string)
    clean_string = []
    s = string.split(/[\s[:punct:]]/)
    s.each do |word|
      #if word.index(/[:punct:]/)
       # split_s = word.split(/[:punct:]/)
        #split_s.each do |subword|
	 # if subword.match(/[\w]+/)[0]
	  #  clean_string << subword.match(/[\w]+/)[0]
	  #end
      	#end
      if word[/[\w]+/]
        clean_string << word[/[\w]+/]
      end
    end
    clean_string
  end

end
