class Raindrops
  def convert(n)
    drop = pling(n) + plang(n) + plong(n)
    @should_print_drop ? drop.join("") : n.to_s
  end

  private
  def text_for_factor(text, factor, n)
    if n % factor == 0
      @should_print_drop = true
      [text]
    else
      []
    end
  end

  def pling(n)
    text_for_factor "Pling", 3, n
  end

  def plang(n)
    text_for_factor "Plang", 5, n
  end

  def plong(n)
    text_for_factor "Plong", 7, n
  end
end
