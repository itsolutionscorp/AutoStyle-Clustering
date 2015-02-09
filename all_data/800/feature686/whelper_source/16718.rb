def combine_anagrams(words)
  z = words.sort_by { |elem| elem.size }
  arr = Array.new(0)
  arr2 = Array.new(0)
  0.upto((z.size - 1)) do |i|
    0.upto((z.size - 1)) do |j|
      if (i != j) then
        if (z[i].unpack("c*").sort.pack("c*").to_s.downcase.sum == z[j].unpack("c*").sort.pack("c*").to_s.downcase.sum) then
          arr.push(z[i])
        else
          arr2.push(z[i])
        end
      end
    end
  end
  p([arr.uniq.sort_by { |elem| elem.size }, (arr2.uniq.sort_by { |elem| elem.size } - arr.uniq.sort_by { |elem| elem.size })])
end

