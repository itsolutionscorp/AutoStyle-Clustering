Year = Struct.new :int do
  def leap?
    int%400 == 0 || int%4 == 0 && int%100 !=0
  end
end
