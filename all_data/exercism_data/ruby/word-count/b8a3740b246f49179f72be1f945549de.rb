class Phrase
  def initialize(input)
    @input = clean(input)
    @hash = {}
    @arr = []
  end

  def word_count
    if @hash == {}
      hashify
    else
      return @hash
      reset_input
      reset_hash
    end
  end

  def hashify
    arrayify
    @arr.each do |word|
      @hash[word] ||= 0
      @hash[word] += 1
    end
    reset_array
    @hash
  end

  def arrayify
    @arr = @input.split(" ")
  end

  def reset_input
    @input = ""
  end

  def reset_array
    @arr = []
  end

  def reset_hash
    @hash = {}
  end

  def clean(input)
    input.strip!
    input.downcase!
    input.gsub!(/,/, " ")
    input.gsub!(/[^a-zA-Z0-9\s]+/, "")
    input
  end

end
