class Hamming
  def self.compute arg1, arg2

    return 0 if arg1 == arg2

    distance = 0

    arg1arr = arg1.split("")
    arg2arr = arg2.split("")



    for i in 0..arg1arr.count
      if arg1arr[i] != arg2arr[i]
        distance += 1
      end
    end

    distance
  end
end
