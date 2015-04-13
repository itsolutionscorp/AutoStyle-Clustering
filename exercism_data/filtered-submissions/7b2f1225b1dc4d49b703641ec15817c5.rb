def compute(*strings)
    sequences = []

    strings.each do |string|
      sequences << string.split('')
    end


    distance = 0


    sequences[0].zip(*sequences[1..-1]).each do |*chars|

      distance += 1 unless chars.flatten.uniq.length == 1
    end

    distance
  end