class Phrase
  attr_reader :words
  def initialize(words="")
    @words = words
  end
  def word_count
    arr = arr_by_sep(valid_chars!)
    arr.uniq.inject({}) do |res, item|
      res[item] = arr.count(item)
      res
    end
  end

  # removes invalid chars
  def valid_chars!
    @words = @words.downcase.delete(@words.gsub(/[a-z ,'0-9]/,""))
  end

  #creates an array based on detect the correct string separator
  def arr_by_sep(str)
    if str =~ /,/
      if str =~ /, /
        return str.gsub(/,/,'').split(" ")
      else
        return str.split(",")
      end
    end
    return str.split(" ")
  end
end
