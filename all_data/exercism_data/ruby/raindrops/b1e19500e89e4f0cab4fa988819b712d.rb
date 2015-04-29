def has_as_primefactor?(value, primefactor)
  value % primefactor == 0
end

class Raindrops
  def self.convert(i)
    output = ''
    output += 'Pling' if has_as_primefactor?(i, 3)
    output += 'Plang' if has_as_primefactor?(i, 5)
    output += 'Plong' if has_as_primefactor?(i, 7)
    if output.empty?
      i.to_s
    else
      output
    end
  end
end
