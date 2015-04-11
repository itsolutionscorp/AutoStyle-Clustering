class Array

  def accumulate(&block)
    # http://blog.codahale.com/2005/11/24/a-ruby-howto-writing-a-method-that-uses-code-blocks/
    res = []
    self.each do |element|
      res << yield(element)
    end
    return res
  end

end
