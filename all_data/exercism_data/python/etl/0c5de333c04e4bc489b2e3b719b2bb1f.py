#Is there a way to use dictionary comprehension to create this new dictionary
#without using list comprehension, or using dict.update()?
def transform(old_dict):
    new_dict = {}
    [[new_dict.update({word.lower():score}) for word in words]
     for score, words in old_dict.items()]
    return new_dict
