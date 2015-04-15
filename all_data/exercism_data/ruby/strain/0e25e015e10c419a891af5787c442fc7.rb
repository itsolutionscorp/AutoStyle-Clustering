class Array
  def keep &block
    self.each_with_object([]) do |item, reponse|
        reponse << item if yield(item)
      end
  end

  def discard &block
    self.each_with_object([]) do |item, reponse|
        reponse << item unless yield(item)
      end
  end
end
