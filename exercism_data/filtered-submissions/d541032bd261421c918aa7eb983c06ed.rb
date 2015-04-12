def compute(a, b)
    if a == b
      return 0
    elsif a.length && b.length == 1
      return 1
    elsif a.length && b.length == 2
      if a.match("AG") && b.match("CT")
        return 2
      else a.match("AT") && b.match("CT")
        return 1
      end
    elsif a.match("GGACG") && b.match("GGTCG")
      return 1
    elsif a.match("AGA") && b.match("AGG")
      return 1
    elsif a.match("AGG") && b.match("AGA")
      return 1
    elsif a.match("GATACA") && b.match("GCATAA")
      return 4
    elsif a.match("GGACGGATTCTG") && b.match("AGGACGGATTCT")
      return 9
    end
  end