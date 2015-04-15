# caches a function's result if the result is truthy
class MemoTrueProc
  def initialize(function, *args)
    @function = function
    @args = args
    @result = false
  end

  def result
    @result || @result = @function.call(*@args)
  end

  def has_result?
    @result ? true : false
  end
end

class Squares

  @@cache = []
  @@functions = { 
    sum_of_squares: proc { | this_number, previous_result| 
      previous_result + this_number ** 2
    },
    square_of_sums: proc { | this_number, previous_result|
      previous_result + this_number**2 * (this_number - 1) + this_number**2
    },
    difference: proc { |this|
      this.square_of_sums - this.sum_of_squares 
    }
  }

  def initialize(n)
    @number = n
    @cache = @@cache[n] || @@cache[n] = self
    @memos = {}
    # wow this is potentially expensive
    if @number > 1
      @cache_previous = @@cache[n-1] || @@cache[n-1] = Squares.new(n-1)
    end

  end
  
  attr :memos

  def method_missing(method_name, *args, &block)
    if ! @@functions.has_key? method_name
      super
    else
      if ! @memos[method_name]
        case @@functions[method_name].arity
        when 2
          @memos[method_name] = MemoTrueProc.new(
            @@functions[method_name], 
            @number,
            @number > 1 ? @cache_previous.send(method_name) : 0
            )
        when 1
          @memos[method_name] = MemoTrueProc.new(
            @@functions[method_name],
            self
            )
        end
      end
      return @memos[method_name].result 
    end
  end
        
end
