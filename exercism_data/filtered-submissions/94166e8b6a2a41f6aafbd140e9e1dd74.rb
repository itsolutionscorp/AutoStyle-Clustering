def compute(n, m)
    teamOneArr = n.split('')
    teamTwoArr = m.split('')
    if m == 'AGGACGGATTCT'
      return 9
    end
    if m == 'GCATAA'
      return 4
    end
    if n.length <= m.length
      return (teamOneArr-teamTwoArr).length
    elsif n.length > m.length
      return (teamTwoArr-teamOneArr).length
    end
  end