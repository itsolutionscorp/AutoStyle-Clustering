class WordProblem
  def initialize(str)
    @str = str
    check_args
    @pemdas = {
      "multiplied" => '*',
      "divided" => "/",
      "plus" => '+',
      "minus" => '-',
    }

  end

  def answer(str = nil)
    # are we calling this recursively or for the first time
    word_array = (str ? str : @str).split


    if all_operations_completed? (word_array)
      # all operations have been completed, return the only number left
      # in the string as the answer
      return word_array.keep_if { |i| i =~ /-\d+|\d+/ }.first.to_i
    end


    word_array.each_with_index do |word, i|
      # loop through opperands in the correct order
      @pemdas.each do |opp_word, opp|
        if opp_word == word
          # this is checking to make sure a num exists at the next array element
          word_array.delete_at(i+1) unless int?(word_array[(i+1)])

          # we clean the nums of the array of ?'s
          nums = clean_nums([word_array[i-1], word_array[(i+1)..word_array.size]].flatten)

          # call the opperand for the given nums dynamically and set the result
          # to the same location
          word_array[i] = send(opp.to_sym, nums)

          # remove the two integers we operated on
          word_array.delete_at(i-1)
          word_array.delete_at(i)

          return answer(word_array.join(" "))
        end
      end
    end
  end

  private

  def all_operations_completed? (arr)
    arr.none? { |word| @pemdas.has_key?(word) }
  end

  def + (arr)
    arr.reduce(:+)
  end

  def - (arr)
    arr.reduce(:-)
  end

  def * (arr)
    arr.reduce(:*)
  end

  def / (arr)
    arr.reduce(:/)
  end

  def clean_nums(arr)
    arr.keep_if { |i| i =~ /-\d+|\d+/ }.map { |i| i.sub("?", "").to_i }.first(2)
  end

  def check_args
    nums = @str.split.keep_if { |i| i =~ /\d+/ }
    raise ArgumentError unless nums.size >= 2
  end

  def int?(i)
    i.sub!("?", "")
    Integer(i) rescue nil
  end

end
