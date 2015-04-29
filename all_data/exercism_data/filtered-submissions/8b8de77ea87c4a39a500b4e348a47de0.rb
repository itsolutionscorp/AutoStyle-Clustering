def compute first, second
    first_bases = first.split(//).take(second.size)
    second_bases = second.split(//)

    results = first_bases.each_with_index.inject([]) do |result, (base, index)|
      result << ((base == second_bases[index]) ? 0 : 1)
      result
    end

    results.inject(:+)
  end