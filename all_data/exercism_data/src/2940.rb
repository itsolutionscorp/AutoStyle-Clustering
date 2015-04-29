def compute(first, second)
      (0...first.length).inject(0) do |acc,i| 
        acc + 1 unless first[i].nil? || second[i].nil? || first[i] == second[i]
      end
      acc
    end