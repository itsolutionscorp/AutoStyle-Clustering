class Hamming

  def self.compute n, m
    n_array = n.split("")
    m_array = m.split("")

    results = n_array.zip(m_array).collect {|x,y| x==y }

    results.count(false)
  end
end
