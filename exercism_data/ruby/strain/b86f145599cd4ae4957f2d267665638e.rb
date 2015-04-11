class Array
  def keep(&predicate)
    filter(invert_selection: false, &predicate)
  end

  def discard(&predicate)
    filter(invert_selection: true, &predicate)
  end

  private

  def filter(invert_selection: false, &predicate)
    accumulator = []
    each do |element|
      accumulator << element if invert_selection ^ predicate.call(element)
    end
    accumulator
  end
end
