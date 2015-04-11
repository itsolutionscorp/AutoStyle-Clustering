class Anagram

  def initialize(arg)
    @arg = arg
  end


  def match(words_array)
    words_array = words_array     
    arg_array = @arg.split("")
    solution_array = []

    words_array.each do |x|

      if @arg == x.downcase
        nil
      elsif arg_array.sort == x.split("").sort.downcase
        solution_array << x
      else
        solution_array
      end

    end
    solution_array


  end

end
