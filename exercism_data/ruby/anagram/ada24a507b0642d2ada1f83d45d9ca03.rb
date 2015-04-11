class Anagram
 
  def initialize(word)
    @@word = word 
  end

  def match(list_of_options)
    anagrams = []
    list_of_options.each do |opt|
      if @@word.downcase.split(//).sort == opt.downcase.split(//).sort && @@word != opt.downcase
        anagrams << opt 
      end
    end
    return anagrams
  end
end
