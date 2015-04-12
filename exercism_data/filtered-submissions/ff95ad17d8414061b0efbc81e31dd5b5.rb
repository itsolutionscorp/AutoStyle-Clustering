def compute(base, other)
    baseList  = base.split("")
    otherList = other.split("")
    i = res = 0
    for value in baseList
      if value != otherList[i]
        res = res + 1
      end
      i = i + 1
    end
    return res
  end