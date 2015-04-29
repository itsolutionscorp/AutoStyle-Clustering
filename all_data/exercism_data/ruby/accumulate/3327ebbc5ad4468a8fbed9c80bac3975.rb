module Accumulate

  def accumulate
    reduce [] do |result, element|
      result.push( yield element )
    end
  end

end

Array.send :include, Accumulate
