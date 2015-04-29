class Array
  def keep
    each_with_object(self.class.new) { |e, memo| memo << e if yield e }
  end

  def discard
    each_with_object(self.class.new) { |e, memo| memo << e unless yield e }
  end
end
