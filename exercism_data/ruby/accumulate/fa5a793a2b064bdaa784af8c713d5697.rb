class Array
  def accumulate &block
    self.each_with_object([]) do |item, reponse|
      reponse << yield(item)
    end
  end
end
