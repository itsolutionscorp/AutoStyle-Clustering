def compute ham1, ham2
    limit = ham1.size > ham2.size ? ham2.size : ham1.size

    ham1[0...limit].chars.zip(ham2[0...limit].chars).reduce(0) do |sum,(a,b)|
      sum + (a.eql?(b) ? 0 : 1)
    end
  end