require 'prime'

class Prime
  def nth(i)
    if i != 0 
      Prime.take(i).last
    else
      message = "You can't do that."
      raise ArgumentError.new(message)
    end
  end
end
