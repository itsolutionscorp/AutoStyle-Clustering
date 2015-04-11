class Array
  def keep
    each_with_object([]){|element, acc|
      acc << element if yield element
    }
  end
  def discard
    each_with_object([]){|element, acc|
      acc << element unless yield element
    }
  end
end
