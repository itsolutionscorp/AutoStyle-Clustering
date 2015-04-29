def word_count(text):
    x = " ".join(text.split())
    text_arr = x.split(" ")
    hist_dict = {}
    for key in text_arr:
        if key in hist_dict:
            hist_dict[key] += 1
        else:
            hist_dict[key] = 1
    return hist_dict
