def add_gigasecond(birthday):
  """ Add 1 gigasecond to the passed birthday """

  from datetime import timedelta

  return birthday + timedelta(0,1E9)
