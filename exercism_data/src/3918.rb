class Hamming
  def compute(str0, str1)
    dist = 0

    for i in 0..(str0.size)
      #
      # I always get confused between ".." and "..."
      # Are there any good way to remember that?
      #
      dist += 1 if str0[i] != str1[i]
    end

    dist
  end
end
